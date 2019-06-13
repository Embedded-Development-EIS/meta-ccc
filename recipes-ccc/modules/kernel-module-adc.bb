SUMMARY = "Kernel driver module for adc through spi"
require kernel-module-eis.inc
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRCREV = "323563de27134a09943cd2ba54118400c55cf964"

KERNEL_MODULE_AUTOLOAD += "${MODULE}"