# ~/.profile: executed by Bourne-compatible login shells.

if [ -f ~/.bashrc ]; then
  . ~/.bashrc
fi

# path set by /etc/profile
# export PATH
export PATH=$PATH:/usr/local/sbin:/usr/sbin:/sbin

mesg n
