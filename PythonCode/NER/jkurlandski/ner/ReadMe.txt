NLTK
You need to install the NLTK package.
- This requires numpy.

SpaCy
(venv) $ pip install spacy
[First error:]
...
  compilation terminated.
  error: command 'x86_64-linux-gnu-gcc' failed with exit status 1

  ----------------------------------------
  Failed building wheel for spacy

http://stackoverflow.com/questions/26053982/error-setup-script-exited-with-error-command-x86-64-linux-gnu-gcc-failed-wit
    $ sudo apt-get install python-dev

        $ sudo apt-get install python-dev
        [sudo] password for jerry:
        Reading package lists... Done
        Building dependency tree
        Reading state information... Done
        python-dev is already the newest version (2.7.11-1).
        The following package was automatically installed and is no longer required:
          ubuntu-core-launcher
        Use 'sudo apt autoremove' to remove it.
        0 upgraded, 0 newly installed, 0 to remove and 7 not upgraded.

    $ sudo apt-get install python3-dev

        This worked.

    (venv) $ pip install spacy

    [First error:]

          File "/home/jerry/workspaceWebsite/jkurlandski/PythonCode/venv/lib/python3.5/site-packages/wheel/bdist_wheel.py", line 161, in get_archive_basename
              impl_tag, abi_tag, plat_tag = self.get_tag()
            File "/home/jerry/workspaceWebsite/jkurlandski/PythonCode/venv/lib/python3.5/site-packages/wheel/bdist_wheel.py", line 155, in get_tag
              assert tag == supported_tags[0]
          AssertionError

          ----------------------------------------
          Failed building wheel for spacy
          Running setup.py clean for spacy


     (venv) $ pip install spacy && python -m spacy.en.download

        Same errors.


     https://bitbucket.org/pypa/wheel/issues/146/wheel-building-fails-on-cpython-350b3

     Led me to update the wheel.

     (venv)$ wheel version
        wheel 0.24.0
        (venv)$ pip update wheel
        ERROR: unknown command "update"

      (venv)$ pip install wheel --upgrade
        Collecting wheel
          Downloading wheel-0.29.0-py2.py3-none-any.whl (66kB)
            100% |████████████████████████████████| 71kB 1.7MB/s
        Installing collected packages: wheel
          Found existing installation: wheel 0.24.0
            Uninstalling wheel-0.24.0:
              Successfully uninstalled wheel-0.24.0
        Successfully installed wheel-0.29.0

      (venv)$ pip install spacy
    Collecting spacy
      Using cached spacy-1.6.0.tar.gz
    Requirement already satisfied: numpy>=1.7 in ./venv/lib/python3.5/site-packages (from spacy)
    Requirement already satisfied: murmurhash<0.27,>=0.26 in ./venv/lib/python3.5/site-packages (from spacy)
    Requirement already satisfied: cymem<1.32,>=1.30 in ./venv/lib/python3.5/site-packages (from spacy)
    Requirement already satisfied: preshed<0.47.0,>=0.46.0 in ./venv/lib/python3.5/site-packages (from spacy)
    Collecting thinc<6.3.0,>=6.2.0 (from spacy)
      Using cached thinc-6.2.0.tar.gz
    Requirement already satisfied: plac in ./venv/lib/python3.5/site-packages (from spacy)
    Requirement already satisfied: six in ./venv/lib/python3.5/site-packages (from spacy)
    Collecting cloudpickle (from spacy)
      Using cached cloudpickle-0.2.2-py2.py3-none-any.whl
    Collecting pathlib (from spacy)
    Collecting sputnik<0.10.0,>=0.9.2 (from spacy)
      Using cached sputnik-0.9.3-py2.py3-none-any.whl
    Collecting ujson>=1.35 (from spacy)
      Using cached ujson-1.35.tar.gz
    Requirement already satisfied: tqdm<5.0.0,>=4.10.0 in ./venv/lib/python3.5/site-packages (from thinc<6.3.0,>=6.2.0->spacy)
    Requirement already satisfied: cytoolz<0.9,>=0.8 in ./venv/lib/python3.5/site-packages (from thinc<6.3.0,>=6.2.0->spacy)
    Requirement already satisfied: flexmock in ./venv/lib/python3.5/site-packages (from thinc<6.3.0,>=6.2.0->spacy)
    Collecting semver (from sputnik<0.10.0,>=0.9.2->spacy)
    Requirement already satisfied: toolz>=0.8.0 in ./venv/lib/python3.5/site-packages (from cytoolz<0.9,>=0.8->thinc<6.3.0,>=6.2.0->spacy)
    Building wheels for collected packages: spacy, thinc, ujson
      Running setup.py bdist_wheel for spacy ... done
      Stored in directory: /home/jerry/.cache/pip/wheels/9c/1a/93/41fad0b4691117752df4acca090024f7e8b8175ac5cc77aae8
      Running setup.py bdist_wheel for thinc ... done
      Stored in directory: /home/jerry/.cache/pip/wheels/14/ea/04/c1596ef3d869372d0fc209d5db0fcdd551151cfc0d620570f5
      Running setup.py bdist_wheel for ujson ... done
      Stored in directory: /home/jerry/.cache/pip/wheels/9e/9b/d0/df92653bb5b2664c15d8ee5b99e3f2eb08a034444db8922b2f
    Successfully built spacy thinc ujson
    Installing collected packages: thinc, cloudpickle, pathlib, semver, sputnik, ujson, spacy
    Successfully installed cloudpickle-0.2.2 pathlib-1.0.1 semver-2.7.6 spacy-1.6.0 sputnik-0.9.3 thinc-6.2.0 ujson-1.35


Runtime errors:

    Model 'en>=1.1.0,<1.2.0' not installed. Please run 'python -m spacy.en.download' to install latest compatible model.

    (venv) $ pip install spacy && python -m spacy.en.download

Now it works.

--------------

NLTK

These are NLTK's requirements.

Use this as the requirement.txt file to get NLTK up and running.

(venv)$ cat requirements.txt
nltk==3.2.2
numpy==1.12.0
six==1.10.0

Then try to install and run SpaCy.




--------------------------------------------

Pycharm complains about needing egenix-mx-base 3.2.9

It's in the PyNLPService.


Collecting egenix-mx-base
  Using cached egenix-mx-base-3.2.9.zip
    Complete output from command python setup.py egg_info:
    Traceback (most recent call last):
      File "<string>", line 1, in <module>
      File "/tmp/pycharm-packaging/egenix-mx-base/setup.py", line 9, in <module>
        import mxSetup, os
      File "/tmp/pycharm-packaging/egenix-mx-base/mxSetup.py", line 229
        print 'running mxSetup.py with setuptools patched distutils'
                                                                   ^
    SyntaxError: Missing parentheses in call to 'print'

    ----------------------------------------

Command "python setup.py egg_info" failed with error code 1 in /tmp/pycharm-packaging/egenix-mx-base/

---------------------------------------------


File > Settings

Project Interpreters


---------------------------------------------

(venv)  $ pip install spacy && python -m spacy.en.download
- Starts, but ends in error.

(venv) -$ python -m spacy.en.download
- Doesn't work.

https://pypi.python.org/pypi/spacy
- Download button
- But says pip should work.

http://stackoverflow.com/questions/15631135/python-h-missing-from-ubuntu-12-04
On Ubuntu 14.04, the command 'sudo apt-get install python3' installed the right
version without having to specify the version the command line


References
Penn Treebank Tag Set
http://www.comp.leeds.ac.uk/amalgam/tagsets/upenn.html