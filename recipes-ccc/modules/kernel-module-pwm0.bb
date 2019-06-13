SUMMARY = "Kernel driver module to generate pwm signal"
require kernel-module-eis.inc
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRCREV = "7f46dc676e640f1e212405564f4f283e26986efb"

KERNEL_MODULE_AUTOLOAD += "${MODULE}"
