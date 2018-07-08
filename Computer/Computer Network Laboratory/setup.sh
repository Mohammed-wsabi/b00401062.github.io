# download dhcp package
apt-get install dhcp3-server

# setup dhcp server interface
vim /etc/default/isc-dhcp-server

# setup dhcp server configuration
vim /etc/dhcp/dhcpd.conf

# start dhcp server
/etc/init.d/isc-dhcp-server start
