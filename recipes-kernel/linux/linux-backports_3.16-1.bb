DESCRIPTION = "Linux Driver backports"
SECTION = "kernel"
LICENSE = "GPLv2"

inherit module

BACKPORTS_CONFIG = "ccgx.config"

SRC_URI = "http://www.kernel.org/pub/linux/kernel/projects/backports/stable/v3.16/backports-3.16-1.tar.xz \
           file://${BACKPORTS_CONFIG}"

SRC_URI[md5sum] = "212c07f28b622d21f41e7a969434c6b0"
SRC_URI[sha256sum] = "acda012f244e7f2c6c383f998d06554c524a0896ab83dd7b1301ef9d284dfd80"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
PR = "r1"

S = "${WORKDIR}/backports-${PV}"
EXTRA_OEMAKE = "KLIB_BUILD=${STAGING_KERNEL_DIR}"

do_configure() {
	cp ${WORKDIR}/${BACKPORTS_CONFIG} ${S}/.config
	oe_runmake CC=cc oldconfig
}

do_install() {
	oe_runmake install INSTALL_MOD_PATH=${D}
}

