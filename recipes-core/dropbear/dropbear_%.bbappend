FILESEXTRAPATHS_prepend := "${THISDIR}/dropbear:"

SRC_URI += "file://authorized_keys"

do_install_append () {
	echo 'DROPBEAR_EXTRA_ARGS="-w -s -g"' > ${D}${sysconfdir}/default/dropbear
	install -d ${D}/home/${ACCOUNT_NAME}/.ssh
	install -m 0600 ${WORKDIR}/authorized_keys ${D}/home/${ACCOUNT_NAME}/.ssh/authorized_keys
}

FILES_${PN} += "/home/${ACCOUNT_NAME}/.ssh/authorized_keys"
