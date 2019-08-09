
### excel open csv digital gibberish

charset is utf-8, but you should add BOM bytes to files first.

```
byte [] bs = { (byte)0xEF, (byte)0xBB, (byte)0xBF};
output.write(bs);
```