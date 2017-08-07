'use strict';

describe('voteApp.version module', function() {
  beforeEach(module('voteApp.version'));

  describe('version service', function() {
    it('should return current version', inject(function(version) {
      expect(version).toEqual('0.1');
    }));
  });
});
