DESCRIPTION = "Venus platform"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit qmakeve
inherit daemontools

DEPENDS = "dbus iptables udev"
RDEPENDS:${PN} += "flashmq"

SRC_URI = " \
    gitsm://github.com/victronenergy/venus-platform.git;branch=master;protocol=ssh;user=git;tag=v${PV} \
    file://can.inc \
    file://0001-start-flashmq-for-testing-purposes.patch \
"
S = "${WORKDIR}/git"

DAEMONTOOLS_RUN = "softlimit -d 100000000 -s 1000000 -a 100000000 ${bindir}/venus-platform"

do_install:append() {
    install -d ${D}/${DAEMONTOOLS_TEMPLATE_CONF_DIR}
    install -m 644 ${WORKDIR}/can.inc ${D}/${DAEMONTOOLS_TEMPLATE_CONF_DIR}

    install -d "${D}/${bindir}"
    install -m 755 ${S}/svectl "${D}/${bindir}"
}

