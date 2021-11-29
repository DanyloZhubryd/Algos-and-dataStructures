class RabinKarp:
    def __init__(self, base: int = 10, mod: int = 13):
        self.base = base
        self.mod = mod

    def to_hash(self, str_to_hash: str):
        str_len = len(str_to_hash)
        hash_result = 0
        for i in range(str_len):
            # hash_result = (hash_result + ord(str_to_hash[i]) * (self.base ** (str_len - i - 1))) % self.mod
            hash_result = (self.base * hash_result + ord(str_to_hash[i])) % self.mod
        return hash_result

    def shift_substring(self, substring: int, first_char_transformer: int, first_symbol: int, next_symbol: int):
        substring = ((substring - first_symbol * first_char_transformer) * self.base + next_symbol) % self.mod
        if substring < 0:
            substring = substring + self.mod
        return substring

    def create_first_char_transformer(self, pattern_len: int):
        transformer = 1
        for i in range(pattern_len-1):
            transformer = (transformer * self.base) % self.mod
        return transformer

    def find(self, text: str, pattern: str):
        str_len = len(text)
        pattern_len = len(pattern)
        first_char_transformer = self.create_first_char_transformer(pattern_len)
        pattern_hash = self.to_hash(pattern)
        substring_hash = self.to_hash(text[0:pattern_len])
        result = []
        j = 0
        for i in range(str_len - pattern_len):
            if pattern_hash == substring_hash:
                for j in range(pattern_len):
                    if text[i + j] == pattern[j]:
                        j += 1
                    else:
                        break
                if j == pattern_len:
                    result.append(i)
            substring_hash = self.shift_substring(substring_hash, first_char_transformer,
                                                  ord(text[i]), ord(text[i+pattern_len]))
        return result


if __name__ == '__main__':
    rabin_karp = RabinKarp()
    result = rabin_karp.find("bibabebybobibo", "bybobi")
    print(result)
