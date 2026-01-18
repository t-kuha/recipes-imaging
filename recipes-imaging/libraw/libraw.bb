# 
# Build LibRaw (0.20)
# 

SUMMARY = "LibRaw"
DESCRIPTION = "LibRaw is a library for reading RAW files obtained from digital photo cameras"

S = "${WORKDIR}/git"

# Fetch source from GitHub
SRC_URI = "git://github.com/LibRaw/LibRaw.git;protocol=https;branch=master"
SRCREV = "0b56545a4f828743f28a4345cdfdd4c49f9f9a2a"

LICENSE = "LGPL-2.1-only | CDDL-1.0"
LIC_FILES_CHKSUM = "file://${S}/COPYRIGHT;md5=1d66195044cfbe4327c055d1c9c1a229"

PV = "0.22.0"
PR = "r0"

DEPENDS += " patchelf-native"

inherit autotools pkgconfig

# Build shared libs only
do_configure() {
    cd ${S}
    autoreconf -i
    ./configure ${CONFIGURE_FLAGS} --prefix=${D}/${prefix} --host=${TARGET_SYS} --enable-static=no
}

do_compile () {
    cd ${S}
    oe_runmake
}

do_install () {
    cd ${S}
    oe_runmake install exec_prefix=${D}/${prefix}
    rm -rf ${D}/${libdir}/pkgconfig
    patchelf --remove-rpath ${D}/${bindir}/4channels
    patchelf --remove-rpath ${D}/${bindir}/dcraw_emu
    patchelf --remove-rpath ${D}/${bindir}/dcraw_half
    patchelf --remove-rpath ${D}/${bindir}/half_mt
    patchelf --remove-rpath ${D}/${bindir}/mem_image
    patchelf --remove-rpath ${D}/${bindir}/multirender_test
    patchelf --remove-rpath ${D}/${bindir}/postprocessing_benchmark
    patchelf --remove-rpath ${D}/${bindir}/raw-identify
    patchelf --remove-rpath ${D}/${bindir}/rawtextdump
    patchelf --remove-rpath ${D}/${bindir}/simple_dcraw
    patchelf --remove-rpath ${D}/${bindir}/unprocessed_raw
}

INHIBIT_PACKAGE_DEBUG_SPLIT = '1'
INSANE_SKIP:${PN} += "already-stripped dev-so"

PACKAGES = " \
    ${PN}     \
    ${PN}-dev \
"

FILES:${PN} += " \
    ${bindir}/* \ 
    ${libdir}/* \
    ${datadir}/* \
"

FILES:${PN}-dev += " \
    ${bindir}/* \
    ${libdir}/* \
    ${includedir}/* \
    ${datadir}/* \
"
