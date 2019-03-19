SUMMARY = "Kernel driver module to generate pwm signal"
require kernel-module-eis.inc
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRCREV = "a1bb33b6d43e1afb78116b7236fb451f387ae0a6"

KERNEL_MODULE_AUTOLOAD += "${MODULE}"
