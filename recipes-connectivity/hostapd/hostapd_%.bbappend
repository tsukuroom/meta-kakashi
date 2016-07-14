FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://hostapd.conf \
	file://defconfig \
"

do_install_append() {
    install -m 0644 ${WORKDIR}/hostapd.conf ${D}${sysconfdir}
}

CONFFILES_${PN} += "${sysconfdir}/hostapd.conf"
