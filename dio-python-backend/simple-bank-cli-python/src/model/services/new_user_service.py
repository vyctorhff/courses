from src.model.exceptions.exceptions import UserExistsError, UserInvalidError

class NewUserService:

    def __init__(self, user_repository):
        self.repository = user_repository

    def add(self, user):
        self.validate_user(user)
        self.validate_user_exists(user)
        self.repository.add(user)

    def validate_user(self, user) -> None:
        if not user.validate():
            raise UserInvalidError('User with fields invalid')
        
    def validate_user_exists(self, user) -> None:
        if self.repository.exists_by_email(user.email):
            raise UserExistsError(f"Already exists an user with this email: {user.email}")
