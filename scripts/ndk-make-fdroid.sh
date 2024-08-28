#!/bin/bash

rustup=$1
ndk=$2
arch=$3
case "$arch" in
    "armeabi-v7a") target=armv7-linux-androideabi;;
    "arm64-v8a") target=aarch64-linux-android;;
    "x86") target=i686-linux-android;;
    "x86_64") target=x86_64-linux-android;;
esac

fdroiddir=chat.delta.lite
rootdir=/home/runner/work/android
repo="$rootdir/android"

mkdir -p "$rootdir"
cd ..
mv "$fdroiddir" "$repo"
pushd "$repo"

"$rustup/rustup-init.sh" -y --default-toolchain $(cat scripts/rust-toolchain) --target $target
source "$HOME/.cargo/env"
export PATH="$PATH:$NDK/toolchains/llvm/prebuilt/linux-x86_64/bin/"
ln -s "$NDK" android-ndk
export ANDROID_NDK_ROOT="$PWD/android-ndk"
scripts/ndk-make.sh $arch

popd
mv $repo $fdroiddir
