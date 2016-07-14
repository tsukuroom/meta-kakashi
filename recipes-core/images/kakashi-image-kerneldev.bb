require kakashi-image-dev.bb

DESCRIPTION = "A small image just capable of allowing a device to boot and \
is suitable for development work."

IMAGE_INSTALL += " \
	kernel-devsrc \
"
