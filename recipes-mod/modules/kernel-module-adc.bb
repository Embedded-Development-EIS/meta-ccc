SUMMARY = "Kernel driver module for adc through spi"
require kernel-module-eis.inc
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRCREV = "4d228bacd60c8c1d30060b09a93c839e975c9591"

# Prefix the module with an underscore to make it load sooner.
do_install_append() {
	install -d ${D}/etc/modules-load.d
	echo "${MODULE}" >> ${D}/etc/modules-load.d/_${MODULE}.conf
}
