KV = "4.4.35"
SRCDATE = "20201204"

PROVIDES = "virtual/blindscan-dvbs"

require maxytec-dvb-modules.inc

SRC_URI[md5sum] = "d1b1ef9676bd54e8c1e46f8db67627d8"
SRC_URI[sha256sum] = "1efc0331f9123bd2d69ab61d075186c3830b0c3c3e942c01949e5bc52c63d175"

COMPATIBLE_MACHINE = "multiboxse"

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
