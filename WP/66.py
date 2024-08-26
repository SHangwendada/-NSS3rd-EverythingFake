import os

def xor_file(filename, key):
    # 读取文件内容
    with open(filename, 'rb') as file:
        data = file.read()

    # 对每个字节进行异或操作
    xored_data = bytearray(b ^ key for b in data)

    # 将处理后的数据写回文件
    with open(filename, 'wb') as file:
        file.write(xored_data)

def main():
    # 获取当前目录
    current_dir = os.getcwd()

    # 要处理的文件名
    filename = os.path.join(current_dir, "Data")

    # 异或的密钥
    key = 0x5c

    # 对文件进行异或处理
    xor_file(filename, key)
    print(f"文件 {filename} 已被异或处理。")

if __name__ == "__main__":
    main()
