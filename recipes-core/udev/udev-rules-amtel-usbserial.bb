SUMMARY = "Automatically loading usbserial module of AMTelecom"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
       file://amtel-usbserial.rules \
"

S = "${WORKDIR}"


do_install() {
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/amtel-usbserial.rules       ${D}${sysconfdir}/udev/rules.d/amtel-usbserial.rules
}

FILES_${PN} = "${sysconfdir}/udev"
RDEPENDS_${PN} = "udev"
