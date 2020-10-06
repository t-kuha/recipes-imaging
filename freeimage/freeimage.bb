# 
# Build FreeImage
# 

SUMMARY = "FreeImage Library"
DESCRIPTION = "Open Source library project for developers who would like to support popular graphics image formats"


S = "${WORKDIR}/FreeImage"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/license-gplv2.txt;md5=1fbed70be9d970d3da399f33dae9cc51"

SRC_URI="http://downloads.sourceforge.net/freeimage/FreeImage3180.zip"
SRC_URI[md5sum] = "f8ba138a3be233a3eed9c456e42e2578"

do_compile () {
    CXX="${CXX}" CC="${CC}" AR="${AR}" oe_runmake
}

# Do not include static library (*.a)
do_install () {
    DESTDIR=${D} oe_runmake install
    rm ${D}${libdir}/*.a
}

#INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN} += "already-stripped dev-so"

PACKAGES = " \
    ${PN}     \
    ${PN}-dev \
"

FILES_${PN} += " \
    ${libdir}/* \
"

FILES_${PN}-dev += " \
    ${libdir}/* \
    ${includedir}/* \
"
