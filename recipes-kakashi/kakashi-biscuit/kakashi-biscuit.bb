LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit npm useradd

B="${S}"

DEPENDS += " nodejs udev"
RDEPENDS_${PN} += " nodejs bash libudev libgphoto2 imagemagick"

SRCBRANCH = "master"
SRCREV = "${AUTOREV}"

SRC_URI = " \
	git://github.com/tsukuroom/kakashi-biscuit.git;protocol=https;branch=${SRCBRANCH} \
	file://alternates \
"

S = "${WORKDIR}/git"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "-d /home/${ACCOUNT_NAME} -m -s /bin/sh -U -P ${ACCOUNT_PW} ${ACCOUNT_NAME}"

do_install() {
	oe_runnpm install

	install -d ${D}/home/${ACCOUNT_NAME}
	cp -r ${S} ${D}/home/${ACCOUNT_NAME}/kakashi-biscuit
	cp -f ${WORKDIR}/alternates ${D}/home/${ACCOUNT_NAME}/kakashi-biscuit/.git/objects/info/
	cp -fR ${KAKASHI_SOUND} ${D}/home/${ACCOUNT_NAME}/kakashi-biscuit/

	if [ -n "${CLARIFAI_ID}" ]; then
		sed -i -e 's/<clarifai_id>/${CLARIFAI_ID}/g' ${D}/home/${ACCOUNT_NAME}/kakashi-biscuit/shell/startup.sh
	fi
	if [ -n "${CLARIFAI_SECRET}" ]; then
		sed -i -e 's/<clarifai_secret>/${CLARIFAI_SECRET}/g' ${D}/home/${ACCOUNT_NAME}/kakashi-biscuit/shell/startup.sh
	fi
}

FILES_${PN} += " \
	/home/${ACCOUNT_NAME} \
"

INSANE_SKIP_${PN} += " \
	staticdev \
"
