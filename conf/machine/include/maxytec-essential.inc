MACHINE_ESSENTIAL_EXTRA_RDEPENDS = " \
	maxytec-dvb-modules-${MACHINE} \
	${@bb.utils.contains('MACHINE_FEATURES', 'hisil', 'maxytec-libs-multibox maxytec-param-multibox maxytec-bootargs-${MACHINE} maxytec-fastboot-${MACHINE} maxytec-logo-multibox maxytec-recovery-${MACHINE}' , '', d)} \
	"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS = "\
	kernel-module-cdfs \
	partitions-by-name \
	resizerootfs \
	maxytec-ethwol \
	${@bb.utils.contains('MACHINE_FEATURES', 'bluetooth', 'kernel-module-btusb bluez5' , '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'maxytec-mali-multibox kernel-module-mali-multibox' , '', d)} \
	\
	firmware-mt7601u \
	firmware-rtl8188eu \
	firmware-rtl8192eu \
	firmware-rtl8723bu \
	firmware-rtl8xxxu \
	\
	kernel-module-8192eu \
	kernel-module-8723bu \
	kernel-module-8821cu \
	kernel-module-88x2bu \
	\
	kernel-module-mt7601u \
	kernel-module-r8723au \
	kernel-module-r8188eu \
	kernel-module-rtl8xxxu \
	"
