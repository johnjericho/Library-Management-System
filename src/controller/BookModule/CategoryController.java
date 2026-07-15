package controller.BookModule;
import java.util.ArrayList;

import model.BookModule.Category;
import services.BookModule.CategoryServices;

public class CategoryController {

	private CategoryServices categoryServices;

	public CategoryController(CategoryServices categoryServices) {
		this.categoryServices = categoryServices;
	}

	public void addCategory(String categoryName) {
		categoryServices.addCategory(categoryName);
	}

	public boolean isExisting(String categoryName) {
		return categoryServices.isExisting(categoryName);
	}

	public ArrayList<Category> loadCategory() {
		return categoryServices.loadCategory();
	}

	public void updateCategory(int categoryId, String categoryName) {
		categoryServices.updateCategory(categoryId, categoryName);
	}

	public void deleteCategory(int categoryId) {
		categoryServices.deleteCategory(categoryId);
	}

	public ArrayList<Category> searchCategory(String keyword) {
		return categoryServices.searchCategory(keyword);
	}
}