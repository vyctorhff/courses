from src.model.domain.user import User
from src.view.messages import NEW_USER_MAIN, NEW_USER_EMAIL, NEW_USER_NAME, NEW_USER_VALUE

class NewUserView:

    def __init__(self):
        pass

    def process(self):
        print(NEW_USER_MAIN)

        name = input(NEW_USER_NAME)
        email = input(NEW_USER_EMAIL)
        value = input(NEW_USER_VALUE)

        self.user = User(name, email, float(value))
    
    def get_user(self):
        return self.user
