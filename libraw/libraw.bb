# 
# Build LibRaw (0.20)
# 

SUMMARY = "LibRaw"
DESCRIPTION = "LibRaw is a library for reading RAW files obtained from digital photo cameras"

S = "${WORKDIR}/LibRaw-${PV}"

SRC_URI="https://www.libraw.org/data/LibRaw-0.20.0.tar.gz"
SRC_URI[md5sum] = "4d9ca72f140ee3be5e5e8f3259d68cf3"

LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://${S}/COPYRIGHT;md5=74c9dffdc42805f9c0de2f97df6031fc  "

PV = "0.20.0"
PR = "r0"

DEPENDS += " patchelf-native"

# Build shared libs only
do_configure() {
    ./configure ${CONFIGURE_FLAGS} --prefix=${D}/${prefix} --host=${TARGET_SYS} --enable-static=no
}

do_compile () {
    oe_runmake
}

do_install () {
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
INSANE_SKIP_${PN} += "already-stripped dev-so"

PACKAGES = " \
    ${PN}     \
    ${PN}-dev \
"

FILES_${PN} += " \
    ${bindir}/* \ 
    ${libdir}/* \
    ${datadir}/* \
"

FILES_${PN}-dev += " \
    ${bindir}/* \
    ${libdir}/* \
    ${includedir}/* \
    ${datadir}/* \
"
