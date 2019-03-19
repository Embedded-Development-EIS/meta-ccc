SUMMARY = "Kernel driver module to for reading and writing input and output signal"
require kernel-module-eis.inc
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRCREV = "a34c9145d50b0cffcafed739f3e1aac6aba54623"

KERNEL_MODULE_AUTOLOAD += "${MODULE}"