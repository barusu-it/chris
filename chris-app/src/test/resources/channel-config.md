

```
# compress and encrypt
tar czvf - <file> | openssl des3 -salt -k <password> -out <gzfile>
# decrypt and decompress
openssl des3 -d -k <password> -salt -in <gzfile> | tar xzf -
```