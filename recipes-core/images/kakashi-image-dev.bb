require kakashi-image.bb

DESCRIPTION = "A small image just capable of allowing a device to boot and \
is suitable for development work."

IMAGE_FEATURES += "dev-pkgs tools-sdk"

IMAGE_INSTALL += " \
	devmem2 \
	tcpdump \
"
