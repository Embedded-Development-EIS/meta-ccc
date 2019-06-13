

# The system-on-module contains flash, CPU+FPGA, RAM and various power supply
# and clock sources.
SOM_FAMILY = "eis-ccc"


# All software packages run on the ARM part, hence strip of the FPGA type from
# the machine same in order to create the MACHINE_ARCH.
MACHINE_BOARD := "${@'-'.join(d.getVar('MACHINE', True).split('-')[:-1])}"

# The kernel also only depends on the board, not the FPGA
KMACHINE = "${MACHINE_BOARD}"

# The FPGA bitstreams will use FPGA_FAMILY as their package-arch, because they
# are compatible with any FPGA of the same type, regardless of the board.
FPGA_FAMILY := "${@d.getVar('MACHINE', True).split('-')[-1:][0]}"
FPGA_FAMILY_ARCH = "${@d.getVar('FPGA_FAMILY', True).replace('-','_')}"

include conf/machine/include/zynq-base.inc

# Use hard-float ABI and allow thumb2 instructions

include conf/machine/include/tune-cortexa9.inc

MACHINEOVERRIDES =. "${SOM_FAMILY}:${MACHINE_BOARD}:${FPGA_FAMILY}:"
PACKAGE_EXTRA_ARCHS_append = " ${FPGA_FAMILY_ARCH}"

