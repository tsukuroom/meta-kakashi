#!/bin/sh

export PATH

if [ $ID_USB_DRIVER = "cdc_mbim" ]; then
  if [ ! -e /etc/mbim-network.conf ]; then
    exit 0
  fi

  case $ACTION in
    add)
        mbim-network /dev/cdc-wdm${INTERFACE: -1} start
        ;;
    remove)
        mbim-network /dev/cdc-wdm${INTERFACE: -1} stop
        ;;
  esac
fi

if grep -q "iface \+$INTERFACE" /etc/network/interfaces; then
  case $ACTION in
    add)
        ifup $INTERFACE
        ;;
    remove)
        ifdown $INTERFACE
        ;;
  esac

  exit 0
fi
