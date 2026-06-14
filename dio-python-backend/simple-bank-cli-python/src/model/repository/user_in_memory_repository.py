from src.model.domain.user import User

class UserInMemoryRepository:

    def __init__(self):
        self.users = list()

    def add(self, user) -> None:
        self.users.append(user)

    def remove(self, email) -> User:
        raise(ValueError, 'not implement')

    def exists_by_email(self, email) -> bool:
        for user in self.users:
            if user.email == email:
                return user
        return False

    def find_by_email(self, email) -> User:
        result_list = list(filter(lambda user: user.email == email, self.users))
        if len(result_list) == 0:
            raise ValueError('not find it')
        return result_list[0] 

    def find_all(self):
        return self.users
