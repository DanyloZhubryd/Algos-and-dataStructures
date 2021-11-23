import re


def get_all_pow(number, str_number):
    return_array = list()
    max_len = len(re.split(r'^0*', str_number)[1])
    square_number = ''
    while len(square_number) <= max_len:
        square_number = bin(number ** len(return_array)).replace('0b', '')
        return_array.append(square_number)
    return return_array[::-1]


def find(number, binary_number_str):
    if number < 0 or number > 100 or len(binary_number_str) > 100:
        return -1
    array_of_pow = get_all_pow(number, binary_number_str)
    count = 0
    while True:
        found_pow_str = ''
        for pow_of_number in array_of_pow:
            if binary_number_str[0:len(pow_of_number)] == pow_of_number:
                found_pow_str = pow_of_number
                count += 1
                break
        if found_pow_str_len := len(found_pow_str):
            binary_number_str = binary_number_str[found_pow_str_len:]
        else:
            if not len(binary_number_str):
                return count
            return -1


if __name__ == '__main__':
    print("Enter the binary number: ")
    input_binary_number_str = input()
    print("Enter the number whose power you want to find: ")
    number_to_find = input()
    print(find(int(number_to_find), input_binary_number_str))

# 100111011110100100111110110011100101000111100101110010001100111011110100100111110110011100101000110010110000111100101110010001