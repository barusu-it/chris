import sys
import base64


def main():
    for f in sys.argv[1:]:
        with open(f, 'rb') as keystore:
            print(" Encoding the File '{}' into a Base64 String ".format(f).center(100, '#'))
            print(base64.b64encode(keystore.read()).decode("utf-8"))
            print(" Finished Encoding ".center(100, '#'))


if __name__ == '__main__':
    main()
