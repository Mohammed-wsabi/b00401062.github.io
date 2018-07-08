apt-get install libncurses5-dev libssl-dev
wget https://cdn.kernel.org/pub/linux/kernel/v4.x/linux-4.4.52.tar.xz
tar Jxvf linux-4.4.52.tar.xz
cd linux-4.4.52
cp /boot/config-$(uname -r) .config
make olddefconfig
cat /proc/cpuinfo | grep processor | wc -l
make bzImage
make modules
make modules_install
make install
mkinitramfs -o /boot/initrd.img-4.4.52 4.4.52
