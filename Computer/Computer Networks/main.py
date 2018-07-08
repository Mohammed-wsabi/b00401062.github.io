import os
import socket
from itertools import combinations

# Define constants
HOST = 'irc.freenode.net'
NICKNAME = 'ROBOT'
IDENTITY = 'ROBOT'
REALNAME = 'ROBOT'

# Read the configuration file
with open('config', 'r') as fin:
    CHANNEL = fin.readline().strip().split('=')[1][1:-1]

# Socket connection
irc = socket.socket(socket.AF_INET, socket.SOCK_STREAM) # Connection protocol
irc.connect((HOST, 6667)) # Connect to host at port 6667

# Send configuration messages to Irssi
irc.send(bytes('NICK %s\r\n' % NICKNAME)) # Nickname
irc.send(bytes('USER %s %s bla :%s\r\n' % (IDENTITY, HOST, REALNAME))) # User information
irc.send(bytes('JOIN :%s\r\n' % CHANNEL)) # Channel
irc.send(bytes('PRIVMSG %s :%s\r\n' % (CHANNEL, 'Hello! I am a robot.'))) # Print hello message

# Handle input cases
while True:
    try: # Handle exceptions
        raw = irc.recv(1024) # Read message from sockets one line at a time
        print raw
        if raw[:4] == 'PING': # Handle PING from Irssi server
            irc.send(bytes('PONG ' + raw.split()[1] + '\r\n'))
            continue
        if not 'PRIVMSG %s' % CHANNEL in raw: continue # Filter PRIVMSG
        if '@repeat' in raw: # @repeat
            arg = raw[raw.find('@repeat') + len('@repeat'):].strip()
            irc.send(bytes('PRIVMSG %s :%s\r\n' % (CHANNEL, arg)))
        elif '@convert' in raw: # @convert
            arg = raw[raw.find('@convert') + len('@convert'):].strip()
            if arg[1] == 'x': irc.send(bytes('PRIVMSG %s :%d\r\n' % (CHANNEL, int(arg, 16)))) # Hex to Dec
            else: irc.send(bytes('PRIVMSG %s :%s\r\n' % (CHANNEL, hex(int(arg))))) # Dec to Hex
        elif '@ip' in raw: # @ip
            arg = raw[raw.find('@ip') + len('@ip'):].strip()
            ips = []
            pos = range(len(arg))[1:]
            for i, j, k in combinations(pos, 3): # Loop through each possible positions
                a, b, c, d = arg[:i], arg[i:j], arg[j:k], arg[k:]
                if int(a) > 255 or int(b) > 255 or int(c) > 255 or int(d) > 255: continue
                ips.append([a, b, c, d])
            irc.send(bytes('PRIVMSG %s :%d\r\n' % (CHANNEL, len(ips))))
            for ip in ips: irc.send(bytes('PRIVMSG %s :%s\r\n' % (CHANNEL, '.'.join(ip))))
        elif '@help' in raw: # @help
            irc.send(bytes('PRIVMSG %s :%s\r\n' % (CHANNEL, '@repeat <Message>')))
            irc.send(bytes('PRIVMSG %s :%s\r\n' % (CHANNEL, '@convert <Number>')))
            irc.send(bytes('PRIVMSG %s :%s\r\n' % (CHANNEL, '@ip <String>')))
    except: pass
