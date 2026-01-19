SUMMARY = "Libvips image processing library"
DESCRIPTION = "libvips is a fast, memory-efficient image processing library."
HOMEPAGE = "https://www.libvips.org/"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1-or-later;md5=2a4f4fd2128ea2f65047ee63fbca9f68"

PV = "8.18.0"
PR = "r0"

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/libvips/libvips.git;protocol=https;branch=master"
SRCREV = "${AUTOREV}"

DEPENDS = "glib-2.0 expat"

inherit meson pkgconfig
