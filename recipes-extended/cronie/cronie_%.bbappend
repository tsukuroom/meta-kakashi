FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://sync_hwclock.sh \
	file://crontab \
"

do_install_append() {
	install -m 0755 ${WORKDIR}/sync_hwclock.sh ${D}${sysconfdir}/cron.hourly/sync_hwclock.sh
}
