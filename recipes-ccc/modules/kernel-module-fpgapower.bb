SUMMARY = "Kernel driver module to indicate that the FPGA has been programmed"
require kernel-module-topic.inc
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8264535c0c4e9c6c335635c4026a8022"

SRCREV = "51581b8c28453a31e6736385810207fbabef1d66"


KERNEL_MODULE_AUTOLOAD += "${MODULE}"