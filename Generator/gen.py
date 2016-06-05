#!/usr/bin/env python

import random
import time
from sys import argv,exit

tiposerv=argv[1]

def random_exp(media) :
	return random.expovariate(1.0/media)

def random_constant(media) :
	return media

if (tiposerv=='M'):
	random_llegada=random_exp
elif (tiposerv=='D'):
	random_llegada=random_constant
else:
	print('uso:')
	print('\t%s M|D lambda tmed numllegadas [seed]'%argv[0])
	exit(0)


l=float(argv[2])
tm=float(argv[3])
n=int(argv[4])


if (len(argv)>5) :
	seed=int(argv[5])
	random.seed(seed)
else:
	random.seed(time.time())

#print("l=%f"%l)
#print("tm=%f"%tm)

t=0

for i in xrange(n) :
	dt=random.expovariate(l)
	s=random_llegada(tm)
	t+=dt
	print('%f %u %f' % (t,i,s))



