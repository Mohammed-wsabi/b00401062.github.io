# clear nat rules
iptables -t nat -F
iptables -t nat -X
iptables -t nat -Z

# enable IP forwarding 
echo 1 > /proc/sys/net/ipv4/ip_forward

# setup nat postrouting
iptables -t nat -A POSTROUTING -s 192.168.0.0/16 -o eth0 -j MASQUERADE

# clear filter rules
iptables -t filter -F
iptables -t filter -X
iptables -t filter -Z

# accept some ports
iptables -A FORWARD -p tcp -m multiport -i eth1 --dport domain,http,ftp,telnet,pop3,smtp -j ACCEPT
iptables -A FORWARD -p tcp -m multiport -i eth1 --sport domain,http,ftp,telnet,pop3,smtp -j ACCEPT
iptables -A FORWARD -p udp -m multiport -i eth1 --dport domain,http,pop3 -j ACCEPT
iptables -A FORWARD -p udp -m multiport -i eth1 --sport domain,http,pop3 -j ACCEPT
iptables -A FORWARD -p icmp -j ACCEPT

# reject other ports
iptables -A FORWARD -i eth1 -j REJECT

# restart dhcp server
/etc/init.d/isc-dhcp-server restart
