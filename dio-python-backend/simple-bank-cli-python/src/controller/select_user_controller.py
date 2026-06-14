from src.view.messages import SELECT_USER_HAS_NO_USER, SELECT_USER, SELECT_USER_ITEM
from src.model.domain.user import User

class SelectUserController:

    def __init__(self, find_user_service, view):
        self.find_user_service = find_user_service
        self.view = view

    def process(self):
        self.users = self.find_user_service.find_all()

        self.view.print_main()
        self.view.print_user(self.users)

    def print_select_user(self):
        self.opt = self.view.print_select_user()
    
    def print_select_user_custom(self, msg):
        self.opt = self.view.print_select_user_custom(msg)
    
    def get_opt(self) -> int:
        return self.view.get_opt()

    def get_user(self) -> User:
        return self.users[self.get_opt()]

class SelectUserView:
    
    def print_main(self):
        print(SELECT_USER)
    
    def print_user(self, users):
        if (len(users) == 0):
            print(SELECT_USER_HAS_NO_USER)
            return
        
        user_grid = ''
        count_tree = 0
        for index, value in enumerate(users):
            user_grid += SELECT_USER_ITEM.format(index, value.name)
            count_tree += 1
            if count_tree % 3 == 0:
                user_grid += "\n"
                continue
            user_grid += "\t"
        
        print(user_grid)

    def print_select_user(self):
        self.opt = input(SELECT_USER)
    
    def print_select_user_custom(self, msg):
        self.opt = input(msg)

    def get_opt(self) -> int:
        return int(self.opt)
