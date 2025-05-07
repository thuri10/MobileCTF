def generate_password():
    password = []
    for i in range(12):
        # Reverse the transformation: pass[len] = (char)(((255 - len) - 100) - pass[len])
        # We know the transformed value must equal '0' (ASCII 48)
        # So: 48 = ((255 - i) - 100) - original_char
        # Therefore: original_char = ((255 - i) - 100) - 48
        original_char = ((255 - i) - 100) - 48
        password.append(chr(original_char))
    return ''.join(password)

if __name__ == "__main__":
    valid_password = generate_password()
    print(f"The valid password is: {valid_password}")