SUMMARY = "Metrological's CPP SDK abstraction layer"
HOMEPAGE = "http://www.metrological.com/"
SECTION = "metrological"
LICENSE = "CLOSED"
#LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS = "zlib"
DEPENDS_append_libc-musl = " libexecinfo"

PV = "1.0+gitr${SRCPV}"

SRC_URI = "git://git@github.com/Metrological/cppsdk.git;protocol=ssh;branch=master"

SRCREV = "90a2c6e1b6263432e6e01e893c136ec305d5f2b6"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

CXXFLAGS += "-D_GLIBCXX_USE_CXX11_ABI=0"

CPPSDK_PLATFORM ?= "platform-pc"
CPPSDK_PLATFORM_mipsel = "platform-dawn"
CPPSDK_PLATFORM_dawn = "platform-dawn"
CPPSDK_PLATFORM_arm = "platform-eos"
CPPSDK_PLATFORM_eos = "platform-eos"
CPPSDK_PLATFORM_rpi = "platform-rpi"

PACKAGECONFIG ?= "${CPPSDK_PLATFORM} cryptalgo generics process tracing websocket rpc"

PACKAGECONFIG[platform-dawn] = "-DCPPSDK_PLATFORM=DAWN,,broadcom-refsw"
PACKAGECONFIG[platform-eos] = "-DCPPSDK_PLATFORM=EOS,,broadcom-refsw"
PACKAGECONFIG[platform-intelce] = "-DCPPSDK_PLATFORM=INTELCE,,intelce-osal intelce-cosai"
PACKAGECONFIG[platform-pc] = "-DCPPSDK_PLATFORM=PC_UNIX,,"
PACKAGECONFIG[platform-rpi] = "-DCPPSDK_PLATFORM=RPI,,virtual/egl"
PACKAGECONFIG[platform-xi5] = "-DCPPSDK_PLATFORM=XI5,,broadcom-refsw"

PACKAGECONFIG[cryptalgo] = "-DCPPSDK_CRYPTALGO=ON,-DCPPSDK_CRYPTALGO=OFF,"
PACKAGECONFIG[debug] = "-DCMAKE_BUILD_TYPE=Debug,-DCMAKE_BUILD_TYPE=Release,"
PACKAGECONFIG[devices] = "-DCPPSDK_DEVICES=ON,-DCPPSDK_DEVICES=OFF,"
PACKAGECONFIG[generics] = "-DCPPSDK_GENERICS=ON,-DCPPSDK_GENERICS=OFF,"
PACKAGECONFIG[tracing] = "-DCPPSDK_TRACING=ON,-DCPPSDK_TRACING=OFF,"
PACKAGECONFIG[unittests] = "-DCPPSDK_UNIT_TESTS=ON,-DCPPSDK_UNIT_TESTS=OFF,"
PACKAGECONFIG[websocket] = "-DCPPSDK_WEBSOCKET=ON,-DCPPSDK_WEBSOCKET=OFF,"
PACKAGECONFIG[rpc] = "-DCPPSDK_RPC=ON,-DCPPSDK_RPC=OFF,"
PACKAGECONFIG[process] = "-DCPPSDK_PROCESS=ON,-DCPPSDK_PROCESS=OFF,"

EXTRA_OECMAKE += " \
    -DINSTALL_HEADERS_TO_TARGET=ON \
"

CXXFLAGS_append_rpi = " -I${STAGING_INCDIR}/interface/vmcs_host/linux"

TOOLCHAIN = "gcc"
