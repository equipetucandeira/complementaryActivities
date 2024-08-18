package com.tucandeira.repository;

import com.tucandeira.db.Repository;
import com.tucandeira.domain.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public final class CategoryRepository implements Repository<Category> {
  private Connection connection;

  public CategoryRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public Category cast(ResultSet resultSet) throws Exception {
    var id = UUID.fromString(resultSet.getString("id"));

    var name = resultSet.getString("name");
    
    var description = resultSet.getString("description");
    
    var submissionLimit = resultSet.getInt("submission_limit");
    
    var workload = resultSet.getInt("workload");

    return new Category(id, name, description, submissionLimit, workload);
  }

  @Override
  public boolean save(Category category) {
    try {
      var statement = this.connection.prepareCall("{call SetCategory(?, ?, ?, ?, ?)}");

      statement.setString(1, category.getID().toString());

      statement.setString(2, category.getName());

      statement.setString(3, category.getDescription());

      statement.setInt(4, category.getSubmissionLimit());

      statement.setInt(5, category.getWorkload());
      
      statement.executeUpdate();

      return true;
    } catch (Exception exception) {
      return false;
    }
  }

  @Override
  public Collection<Category> list() {
    Collection<Category> categories = new ArrayList<>();

    try {
     var statement = this.connection.prepareCall("{call GetCategories()}");

     var resultSet = statement.executeQuery();

     while (resultSet.next()) {
      categories.add(cast(resultSet));
     }
    } catch (Exception exception) {
      return new ArrayList<Category>();
    }

    return categories;
  }

  @Override
  public Optional<Category> find(UUID id) {
    try {
      var statement = this.connection.prepareCall("{call GetCategory(?)}");

      statement.setString(1, id.toString());

      var resultSet = statement.executeQuery();

      return Optional.of(cast(resultSet));
    } catch (Exception exception) {
      return Optional.ofNullable(null);
    }
  }

  @Override
  public boolean update(Category category) {
    try {
      var statement = this.connection.prepareCall("{call UpdateCategory(?, ?, ?, ?, ?)}");

      statement.setString(1, category.getID().toString());

      statement.setString(2, category.getName());

      statement.setString(3, category.getDescription());

      statement.setInt(4, category.getSubmissionLimit());

      statement.setInt(5, category.getWorkload());

      statement.executeUpdate();

      return true;
    } catch (Exception exception) {
      return false;
    }
  }

  @Override
  public boolean delete(UUID id) {
    try {
      var statement = this.connection.prepareStatement("{call DeleteCategory(?)}");

      statement.setString(1, id.toString());

      statement.executeUpdate();

      return true;
    } catch (Exception exception) {
      return false;
    }
  }
}
