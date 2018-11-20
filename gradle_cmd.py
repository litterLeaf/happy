# -*- coding:utf-8 -*-
import os


def execute_cmd():
    # tmp = os.popen('gradlew assembleRelease')
    # print(tmp)
    os.system('gradlew assembleRelease')

execute_cmd()
