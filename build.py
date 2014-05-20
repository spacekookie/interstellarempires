#!/usr/bin/python

import platform
import sys
from subprocess import call
import subprocess
import os
from time import sleep
import textwrap

print "######################################\n"
init = "Welcome to the Interstellar Empires development build script. Run this script as root to setup your workspace to develop on the game."

username = os.getlogin()
system = platform.system()
rows, columns = os.popen('stty size', 'r').read().split()
consoleWidth = int(float(columns))

if system == "Darwin":
	sysIdent = "Looks like you're running this on a (%s) Mac! :)" % system
	welcome = "If you let this script run as root it will install homebrew and maven for you before setting up your workspace. If you don't want to run this script with root privileges then you should make sure you already have them installed."
elif system == "Linux":
	sysIdent = "Looks like you're running this on Linux! :)"
	welcome = "On Debian based systems you can let this script install maven for you (this will require root access). If you don't want to run this script as root you should make sure to have maven already installed."
else:
	endList = textwrap.wrap("Your OS wasn't regnognized. Are you running Windows? Good luck with that! :)\n\nCanceling build NOW!", consoleWidth)
	for element in endList:
		print element
	sys.exit()
finish = "Would you like to continue with root access? [y/N]: "

# Now to print everything properly
initList = textwrap.wrap(init, width=consoleWidth)
for element in initList:
	print(element)

print " "
sleep(0.5)

sysList = textwrap.wrap(sysIdent, width=consoleWidth)
for element in sysList:
	print(element)


print " "
sleep(0.75)

welcomeList = textwrap.wrap(welcome, width=consoleWidth)
for element in welcomeList:
	print(element)

usrInput = raw_input(finish)
usrInput.lower()
hasRoot = False


if usrInput.lower() == "n":
	hasRoot = False
elif usrInput.lower() == "y":
	hasRoot = True
elif usrInput.lower() == "":
	hasRoot = False
else:
	print "Invalid input. Canceling build"
	sys.exit()

thx = "'I have root now. Thank you " + username + ". I promise not to kill you :)\n'"

if hasRoot:
	call("sudo echo " + thx, shell=True)
	if system == "Darwin":
		error1 = "-bash: brew: command not found"
		cmd1 = [ 'brew', '--version' ]
		output1 = subprocess.Popen( cmd1, stdout=subprocess.PIPE ).communicate()[0]
		if output1 == error1:
			call("ruby -e '$(curl -fsSL https://raw.github.com/Homebrew/homebrew/go/install))'", shell=True)

		error2 = "-bash: mvn: command not found"
		cmd2 = [ 'mvn', '--version' ]
		output2 = subprocess.Popen( cmd2, stdout=subprocess.PIPE ).communicate()[0]
		if output2 == error2:
			call("brew install maven", shell=False)
			print "Setup finished"
	elif system == "Linux":
		call("sudo apt-get install maven", shell=True)
		print "Setup should now be finished."

call("cd Framework && mvn install", shell=True)
call("cd R2D && mvn install", shell=True)
call("cd Client && mvn compile", shell=True)

ending = "Packaging should now be complete. If you encounter errors with this script please report them on our RedMine page @ https://support.2rsoftworks.de"

endingList = textwrap.wrap(ending, width=consoleWidth)
print "\n"
for element in endingList:
	print(element)
print "\n"

sys.exit()