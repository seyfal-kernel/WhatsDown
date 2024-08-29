#!/bin/bash

RUSTUP=$1
NDK=$2
ARCH=$3
case "$ARCH" in
    "armeabi-v7a") TARGET=armv7-linux-androideabi;;
    "arm64-v8a") TARGET=aarch64-linux-android;;
    "x86") TARGET=i686-linux-android;;
    "x86_64") TARGET=x86_64-linux-android;;
esac

FDROIDDIR=chat.delta.lite
ROOTDIR=/home/runner/work/android
REPO="$ROOTDIR/android"

mkdir -p "$ROOTDIR"
cd ..
mv "$FDROIDDIR" "$REPO"
pushd "$REPO"

"$RUSTUP/rustup-init.sh" -y --default-toolchain $(cat scripts/rust-toolchain) --target $TARGET
source "$HOME/.cargo/env"
export PATH="$PATH:$NDK/toolchains/llvm/prebuilt/linux-x86_64/bin/"
export ANDROID_NDK_ROOT="$NDK"
scripts/ndk-make.sh $ARCH

popd
mv $REPO $FDROIDDIR
