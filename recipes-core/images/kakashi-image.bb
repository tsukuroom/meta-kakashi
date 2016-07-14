SUMMARY = "Minimal image for THETA kakashi"

IMAGE_INSTALL = " \
	packagegroup-core-boot \
	${ROOTFS_PKGMANAGE_BOOTSTRAP} \
	${CORE_IMAGE_EXTRA_INSTALL} \
	kernel-modules \
	linux-firmware-ralink \
	usbutils \
	kakashi-rc-local \
	udev-rules-autousbnet \
	udev-rules-amtel-usbserial \
	udev-rules-autossid \
	libmbim \
	dropbear \
	resolvconf \
	ntp \
	hostapd \
	tzdata \
	coreutils \
	cronie \
	sudo \
	nodejs \
	nodejs-npm \
	gphoto2 \
	libgphoto2-dev \
	imagemagick \
	opencv \
	alsa-utils \
	git \
	screen \
	kakashi-biscuit \
"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image extrausers

IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"

EXTRA_USERS_PARAMS = " \
	usermod -L root; \
"

ROOTFS_POSTPROCESS_COMMAND += " \
	add_user_to_sudoers; \
	change_owener; \
"

add_user_to_sudoers () {
	echo >> ${IMAGE_ROOTFS}${sysconfdir}/sudoers
	echo "${ACCOUNT_NAME} ALL=(ALL) NOPASSWD: ALL" >> ${IMAGE_ROOTFS}${sysconfdir}/sudoers
}

change_owener () {
	chown -R ${ACCOUNT_NAME}:${ACCOUNT_NAME} ${IMAGE_ROOTFS}/home/${ACCOUNT_NAME}
}
