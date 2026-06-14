class Console:
    """
    Class create to handler input and print operations
    Also create to make possible testing controllers and view with mock
    """
    
    def __init__(self):
        pass

    def get_input_as_int(self, msg):
        return int(input(msg))
    
    def get_input_as_float(self, msg):
        return float(input(msg))
    
    def do_print(self, msg):
        print(msg)
