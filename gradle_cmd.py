# -*- coding:utf-8 -*-
import os, shutil

# 打包默认生成的apk路径
apk = './app/build/outputs/apk/release/app-release.apk'
# 打包完成复制到的目标路径 按需修改
copyApk = 'C:/apks/app-release.apk'


def execute_cmd():
    # tmp = os.popen('gradlew assembleRelease')
    # print(tmp)
    # os.system('gradlew assembleRelease')
    output = os.popen('gradlew assembleRelease')
    ret = output.read()
    print(ret)
    if 'BUILD SUCCESSFUL' in ret:
        print('打包成功')
        shutil.copyfile(apk, copyApk)
    else:
        print('打包失败')


if __name__ == '__main__':
    execute_cmd()
