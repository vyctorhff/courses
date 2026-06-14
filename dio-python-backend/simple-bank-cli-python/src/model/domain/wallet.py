class Wallet:

    def __init__(self, current_value):
        self.current_value = current_value
        self.check_not_negative()

    def get_value(self):
        return self.current_value

    def debit(self, value):
        self.current_value -= value

    def credit(self, value):
        self.current_value += value

    def check_not_negative(self):
        if (self.current_value < 0.0):
            raise ValueError('Current value invalid')
