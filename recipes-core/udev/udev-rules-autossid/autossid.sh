#!/bin/sh

case $ACTION in
  add)
    # get hwaddr
    linkether=`ip addr show ${INTERFACE} | grep link/ether`
    hwaddr=`echo ${linkether} | sed -e s/://g | sed -E "s@.*link/ether\s(\S+)(\s.*|$)@\1@g"`
    # overwrite ssid
    sed -i -e "s/kakashi_.*/kakashi_${hwaddr}/g" /etc/hostapd.conf
    # restart hostapd
    /etc/init.d/hostapd restart
    ;;
  remove)
    /etc/init.d/hostapd stop
    ;;
esac
