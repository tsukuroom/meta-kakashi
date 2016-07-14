SUMMARY = "Auto network interface updown"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
       file://autousbnet.rules \
       file://ifupdown-usbnet.sh \
"

S = "${WORKDIR}"


do_install() {
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/autousbnet.rules       ${D}${sysconfdir}/udev/rules.d/autousbnet.rules

    install -d ${D}${sysconfdir}/udev/scripts/
    install -m 0755 ${WORKDIR}/ifupdown-usbnet.sh ${D}${sysconfdir}/udev/scripts
}

FILES_${PN} = "${sysconfdir}/udev"
RDEPENDS_${PN} = "udev"
