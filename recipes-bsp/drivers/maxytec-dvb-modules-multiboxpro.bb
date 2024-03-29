KV = "4.4.35"
SRCDATE = "20211207"

PROVIDES = "virtual/blindscan-dvbs"

require maxytec-dvb-modules.inc

SRC_URI[md5sum] = "4898a370328151cf57cea93d772b2049"
SRC_URI[sha256sum] = "16a9175d384baace4d35f00fbcfd392898d0c17aad8b698f36e38ea496475395"

COMPATIBLE_MACHINE = "multiboxpro"

INITSCRIPT_NAME = "suspend"
INITSCRIPT_PARAMS = "start 89 0 ."
inherit update-rc.d

do_configure[noexec] = "1"

# Generate a simplistic standard init script
do_compile_append () {
	cat > suspend << EOF
#!/bin/sh

runlevel=runlevel | cut -d' ' -f2

if [ "\$runlevel" != "0" ] ; then
	exit 0
fi

mount -t sysfs sys /sys

/usr/bin/turnoff_power
EOF
}

do_install_append() {
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${bindir}
	install -m 0755 ${S}/suspend ${D}${sysconfdir}/init.d
	install -m 0755 ${S}/turnoff_power ${D}${bindir}
}

do_package_qa() {
}

FILES_${PN} += " ${bindir} ${sysconfdir}/init.d"

INSANE_SKIP_${PN} += "already-stripped"
