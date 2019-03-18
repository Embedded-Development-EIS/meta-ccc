DESCRIPTION = "Xilinx Zynq kernel with EIS extensions"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

KBRANCH = "eis-ccc"
SRCREV = "e4f86703a6a15f9044bf03e08f9330a31b0b1947"
LINUX_VERSION = "4.9"

inherit kernel
require recipes-kernel/linux/linux-dtb.inc

S = "${WORKDIR}/git"

# Using LZO compression in the kernel requires "lzop"
DEPENDS += "lzop-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-eis:"

# If you have a local repository, you can set this variable to point to
# another kernel repo. Or to another kernel entirely.

KERNEL_GIT_REPO ?= "git://github.com/Embedded-Development-EIS/linux"

SRC_URI = "\
	${KERNEL_GIT_REPO};branch=${KBRANCH} \
	file://defconfig \
	"
KERNEL_IMAGETYPE = "uImage"

KERNEL_DEVICETREE = "eis-ccc.dtb"
KERNEL_DEVICETREE_eis-ccc = "\
	eis-ccc.dtb \
	"
# See http://permalink.gmane.org/gmane.linux.kernel.commits.head/371588
KERNEL_EXTRA_ARGS += "LOADADDR=0x00008000"
KERNEL_IMAGEDEST = "tmp/boot"

FILES_kernel-image = "${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}*"

LINUX_VERSION_EXTENSION ?= "-eis"

PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE = "eis-ccc"

KERNEL_FLASH_DEVICE = "/dev/mtd4"

pkg_postinst_kernel-image () {
	if [ "x$D" == "x" ]; then
		if [ -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION} ] ; then
			if grep -q "ubi0:qspi-rootfs" /proc/mounts
			then
				flashcp -v /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION} ${KERNEL_FLASH_DEVICE}
			else
				if [ -f /media/mmcblk0p1/${KERNEL_IMAGETYPE} ]; then
					cp /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION} /media/mmcblk0p1/${KERNEL_IMAGETYPE}
				fi
			fi
			rm -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION}
		fi
	fi
	true
}

# Workaround: Enforce using "our" defconfig and not some stale version from a
# previous build. Pending real fix in OE-core.
do_configure_prepend() {
	cp "${WORKDIR}/defconfig" "${B}/.config"
}
